package cs307.com.playdeck;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class AsyncTaskLobby implements Runnable {
    @Override
    public void run() {
        try {
            ServerSocket servSocket = new ServerSocket(3457);
            Socket socket = servSocket.accept();

            //If this code is reached, it means we have gotten a message

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = br.readLine();
        }
        catch(Exception e){
        }

    }
}