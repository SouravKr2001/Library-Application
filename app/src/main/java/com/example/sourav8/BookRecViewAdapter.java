package com.example.sourav8;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.sourav8.BookActivity.BOOK_ID_KEY;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder>{
    private static final String TAG= "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private Context mcontext;
    private String parentActivity;

    public BookRecViewAdapter(Context mcontext, String parentActivity) {
        this.mcontext = mcontext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder : called");
        holder.txtBookName.setText(books.get(position).getName());
        Glide.with(mcontext)
                .asBitmap()
                .load(books.get(position).getImageUrl())



                .into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,BookActivity.class);
                intent.putExtra(BOOK_ID_KEY,books.get(position).getId());

                mcontext.startActivity(intent);
            }
        });


        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtshort.setText(books.get(position).getAuthor());
        if(books.get(position).isExpanded())
        {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.VISIBLE);
            holder.btnDownArrow.setVisibility(View.GONE);

            if(parentActivity.equals("allBooks"))
            {
                holder.btnDelete.setVisibility(View.GONE);
            }
            else if(parentActivity.equals("alreadyRead"))
            {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(mcontext);
                        builder.setMessage("Are you sure u want to delete this book"+books.get(position).getName()+"?");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(Utils.getInstance(mcontext).removeFromAlraedyRead(books.get(position)))
                                {
                                    Toast.makeText(mcontext, "book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }

                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });
            }
            else if (parentActivity.equals("wantsToRead"))
            {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(mcontext);
                        builder.setMessage("Are you sure u want to delete this book"+books.get(position).getName()+"?");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(Utils.getInstance(mcontext).removeFromWantToRead(books.get(position)))
                                {
                                    Toast.makeText(mcontext, "book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }

                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });
            }
            else if(parentActivity.equals("currentlyReading"))
            {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(mcontext);
                        builder.setMessage("Are you sure u want to delete this book"+books.get(position).getName()+"?");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(Utils.getInstance(mcontext).removeFromCurrentlyReading(books.get(position)))
                                {
                                    Toast.makeText(mcontext, "book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }

                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });
            }
            else
            {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(mcontext);
                        builder.setMessage("Are you sure u want to delete this book"+books.get(position).getName()+"?");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(Utils.getInstance(mcontext).removeFromFavourite(books.get(position)))
                                {
                                    Toast.makeText(mcontext, "book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }

                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });
            }
        }
        else
        {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.GONE);
            holder.btnDownArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView parent;
        private ImageView imgBook;
        private TextView txtBookName;
        private ImageView btnDownArrow,btnUpArrow;
        private RelativeLayout  expandedRelativeLayout;

        private TextView txtAuthor,txtshort;

        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            imgBook=itemView.findViewById(R.id.imgBook);
            txtBookName=itemView.findViewById(R.id.txtBookName);

            btnDownArrow=itemView.findViewById(R.id.btnDownArrow);
            btnUpArrow=itemView.findViewById(R.id.btnUpArrow);
            expandedRelativeLayout=itemView.findViewById(R.id.expandedRelativeLayout);

            txtAuthor =itemView.findViewById(R.id.txtauthor);
            txtshort=itemView.findViewById(R.id.txtshort);

            btnDelete=itemView.findViewById(R.id.btnDelete);
            btnDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book=books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });


            btnUpArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book=books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });



        }
    }
}
