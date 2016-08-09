package com.shuivy.happylendandreadbooks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.adapter.ChatListAdapter;
import com.shuivy.happylendandreadbooks.models.MyMessage;
import com.shuivy.happylendandreadbooks.util.ChatListBuilder;
import com.shuivy.happylendandreadbooks.util.MessageListBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stk on 2016/8/7 0007.
 */
public class ChatActivity extends AppCompatActivity{
    EditText inputEditText;
    ImageView sendImageView;
    TextView guestNameTextView;
    TextView titleTextView;
    ArrayList<MyMessage>  chatMessages = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        Intent intent=getIntent();
        final String guestCode=intent.getStringExtra("guestCode");
        final String guestName=intent.getStringExtra("guestName");

        ChatListBuilder messageListBuilder=new ChatListBuilder(this);
        chatMessages =(ArrayList<MyMessage>)messageListBuilder.getMessages(guestCode);
        ListView chatListView=(ListView)findViewById(R.id.chat_list_view);
        final ChatListAdapter chatListAdapter=new ChatListAdapter(chatMessages,this);
        chatListView.setAdapter(chatListAdapter);

        titleTextView=(TextView)findViewById(R.id.chat_header_text);
//        titleTextView.setText(guestName);


        inputEditText=(EditText)findViewById(R.id.input_edit_text);
        sendImageView=(ImageView)findViewById(R.id.send_image_view);
        sendImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=inputEditText.getText().toString();
                if(text.trim().length()==0)
                    return;
                MyMessage message=new MyMessage();
                message.setGuestCode(guestCode);
                message.setGuestName(guestName);
                message.setType(0);
                message.setContent(text);
                message.setDate(new Date().getTime());
                chatMessages.add(message);
                if(message.save(ChatActivity.this)){
                    if(chatListAdapter!=null)
                        chatListAdapter.notifyDataSetChanged();
                    inputEditText.setText("");
                    setResult(1);
                }

            }
        });




    }
}
