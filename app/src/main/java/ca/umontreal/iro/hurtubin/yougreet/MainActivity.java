package ca.umontreal.iro.hurtubin.yougreet;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Optional;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> friends;
    ArrayAdapter listAdapter;
    ArrayAdapter adapter;

    @Nullable
    @BindView(R.id.text)
    TextView text;

    @Nullable
    @BindView(R.id.nb_friends)
    TextView nb_friends;

    @Nullable
    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    @Nullable
    @BindView(R.id.list)
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if(list == null)
            return;

        friends = new ArrayList<>();

        String text_nb_friends = getResources().getQuantityString(R.plurals.nb_friends, friends.size(), friends.size());

        nb_friends.setText(text_nb_friends);

        adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                friends
        );

        autoCompleteTextView.setAdapter(adapter);


        listAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                friends
        );

        list.setAdapter(listAdapter);
    }

    @Optional
    @OnItemClick(R.id.list)
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String name = friends.get(i);

        autoCompleteTextView.setText(name);
    }

    @Optional
    @OnClick(R.id.button)
    public void onButtonClick(Button btn) {
        String name = autoCompleteTextView.getText().toString();

        String greetting = getResources().getString(R.string.greeting, name);

        Toast.makeText(this, greetting, Toast.LENGTH_SHORT).show();
    }

    @Optional
    @OnClick(R.id.add)
    public void onFriendAdded(Button btn) {
        String name = autoCompleteTextView.getText().toString();

        friends.add(name);
        listAdapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();

        String text_nb_friends = getResources().getQuantityString(R.plurals.nb_friends, friends.size(), friends.size());

        nb_friends.setText(text_nb_friends);
    }
}