package com.nospoon.jpromises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class AsyncContext {

    private final List<Object> chain   = new ArrayList<>();
    private final List<Object> results = new ArrayList<>();

    public AsyncContext(Function f) {
        this.then(f);
    }

    public <A, B> AsyncContext then(Function<A, B> f) {
        this.chain.add(f);
        return this;
    }

    public <A> AsyncContext then(Consumer<A> f) {
        this.chain.add(f);
        return this;
    }

    public AsyncContext sync(Action f) {
        this.chain.add(f);
        return this;
    }

    public <A, B, C> AsyncContext then(BiFunction<A, B, C> f) {
        this.chain.add(f);
        return this;
    }

    public <A, B> AsyncContext then(BiConsumer<A, B> f) {
        this.chain.add(f);
        return this;
    }

    public <A, B> AsyncContext parallel(Function<A, B> f) {
        lastList().add(f);
        return this;
    }

    public <A, B, C> AsyncContext parallel(BiFunction<A, B, C> f) {
        lastList().add(f);
        return this;
    }

    private List lastList() {
        if (this.chain.isEmpty()) {
            throw new IllegalStateException("Cannot use parallel here!");
        }
        int    lastIdx = this.chain.size() - 1;
        Object last    = this.chain.get(lastIdx);
        List   lastList;
        if (last instanceof List) {
            lastList = (List) last;
        } else {
            lastList = new ArrayList<>();
            lastList.add(last);
            this.chain.set(lastIdx, lastList);
        }
        return lastList;
    }

    public <T> Promise<T> promise() {
        if (chain.isEmpty()) {
            return Promises.resolve(null);
        }

        Object next = chain.remove(0);
        if (next instanceof Function) {
            Function f = (Function) next;
            return ((Promise) f.apply(result(0))).pipe(res -> {
                results.add(0, res);
                return promise();
            });
        } else if (next instanceof Consumer) {
            Consumer f = (Consumer) next;
            f.accept(result(0));
            return promise();
        } else if (next instanceof BiConsumer) {
            BiConsumer f = (BiConsumer) next;
            f.accept(result(0), result(1));
            return promise();
        } else if (next instanceof BiFunction) {
            BiFunction f = (BiFunction) next;
            return ((Promise) f.apply(result(0), result(1))).pipe(res -> {
                results.add(0, res);
                return promise();
            });
        } else if (next instanceof List) {
            List<Promise> promises = new ArrayList<>();
            for (Object nextChild : ((List) next)) {
                if (nextChild instanceof Function) {
                    promises.add((Promise) ((Function) nextChild).apply(result(0)));
                } else if (nextChild instanceof BiFunction) {
                    promises.add((Promise) ((BiFunction) nextChild).apply(result(0), result(1)));
                }
            }
            return Promises.all(promises).pipe(res -> {
                res.forEach(result -> results.add(0, result));
                return promise();
            });
        } else if (next instanceof Action) {
            Action f = (Action) next;
            f.exec();
            return promise();
        } else {
            throw new IllegalStateException("Cannot handle next chain element: " + next);
        }
    }

    private Object result(int idx) {
        return results.isEmpty() ? null : results.get(idx);
    }

    @FunctionalInterface
    public interface Action {

        /**
         * Gets a result.
         *
         * @return a result
         */
        void exec();
    }

}