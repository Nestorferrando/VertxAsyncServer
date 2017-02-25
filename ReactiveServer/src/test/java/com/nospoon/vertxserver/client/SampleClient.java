package com.nospoon.vertxserver.client;

import com.nospoon.jpromises.Deferred;
import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.vertxserver.messages.MessageKt;
import com.nospoon.vertxserver.messages.fromclient.Ping;
import io.vertx.core.net.NetSocket;

/**
 * Created by Nestor on 7/31/2016.
 */
public class SampleClient {

    private int messageAmount;
    private NetSocket clientSocket;
    private int messages;

    public SampleClient(int messageAmount, NetSocket clientSocket) {
        this.messageAmount = messageAmount;
        this.clientSocket = clientSocket;
        this.messages = 0;
    }

    public Promise<Void> performSession() {

        Deferred<Void> deferred = Promises.defer();

        clientSocket.handler(buffer -> {
            MessageKt.deserialize(buffer.getString(0, buffer.length())).forEach(message -> {

                System.out.println("envio mensaje num "+messages );
                clientSocket.write(MessageKt.serialize(new Ping("ping num " + messages)));
                messages++;
                if (messages == messageAmount) {
                    clientSocket.close();
                    deferred.resolve(null);
                }

            });

        });

        clientSocket.write(MessageKt.serialize(new Ping("ping num " + messages)));
        return deferred.promise();
    }

}
