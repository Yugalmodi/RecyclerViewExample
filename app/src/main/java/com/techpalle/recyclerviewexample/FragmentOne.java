package com.techpalle.recyclerviewexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    EditText editTextActorName, editTextMovieName;
    RecyclerView recyclerView;
    Button buttonAdd;
    bollywood bolly;
    ArrayList<bollywood> al;
    MyAdapter adapter;
    RecyclerView.ViewHolder myViewHolder;

    public FragmentOne() {
        // Required empty public constructor
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        public MyAdapter(){
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v =getActivity().getLayoutInflater().inflate(R.layout.row, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            bolly = al.get(position);
            holder.tv1.setText(position+1 + ".  ");
            holder.tv2.setText("Actor : "+bolly.getActorName());
            holder.tv3.setText("Movie : "+bolly.getMovieName());
        }

        @Override
        public int getItemCount() {
            return al.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv1, tv2, tv3;
            public ViewHolder(View itemView) {
                super(itemView);
                tv1 = (TextView) itemView.findViewById(R.id.text_view_no);
                tv2 = (TextView) itemView.findViewById(R.id.text_view_actor_name);
                tv3 = (TextView) itemView.findViewById(R.id.text_view_movie_name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        editTextActorName = (EditText) v.findViewById(R.id.edit_text_actor_name);
        editTextMovieName = (EditText) v.findViewById(R.id.edit_text_movie_name);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        buttonAdd = (Button) v.findViewById(R.id.button_add);
        al = new ArrayList<bollywood>();
        adapter = new MyAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bolly = new bollywood();
                bolly.setActorName(editTextActorName.getText().toString().trim());
                bolly.setMovieName(editTextMovieName.getText().toString().trim());
                al.add(bolly);
                recyclerView.setAdapter(adapter);
                adapter.notifyItemInserted(al.size()-1);
            }
        });
        return v;
    }
}
