package com.socketiochat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {
    Socket socket;
    ListView listView;
    EditText editText;
    List<String> listOfMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        listOfMessages = new ArrayList<>();
        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, listOfMessages);
        listView.setAdapter(adapter);
        setSocketIO();
        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                socket.emit("chat message", editText.getText().toString());
                editText.setText("");
            }
        });
    }

    void setSocketIO() {
        try {
            socket = IO.socket("http://192.168.134.85:3000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    Log.d("", "Connected");
                    socket.emit("chat message", "Mobile Device Connected : Hi from android");
                }

            }).on("chat message", new Emitter.Listener() {

                @Override
                public void call(final Object... args) {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MySimpleArrayAdapter mySimpleArrayAdapter = (MySimpleArrayAdapter) listView.getAdapter();
                            mySimpleArrayAdapter.add(args[0].toString());
                            mySimpleArrayAdapter.notifyDataSetChanged();
                        }
                    });
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                }

            });
            socket.connect();
        } catch (Exception ex) {

        }
    }

    public class MySimpleArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private List<String> values;

        public MySimpleArrayAdapter(Context context, List<String> values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.grid_item, parent, false);
            TextView textView = rowView.findViewById(R.id.txtMessage);
            try {
                textView.setText(values.get(position));
                textView.setTag(values.get(position));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return rowView;
        }

        @Override
        public void clear() {
            values.clear();
        }

        @Override
        public void add(@Nullable String object) {
            values.add(object);
        }

        @Override
        public void addAll(@NonNull Collection<? extends String> collection) {
            values.addAll(collection);
        }

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }
    }

}
