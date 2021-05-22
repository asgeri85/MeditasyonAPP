package com.example.meditasyonapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditasyonapp.Activities.DetayActivity;
import com.example.meditasyonapp.Classes.Meditasyonlar;
import com.example.meditasyonapp.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.AdapterTutucu> {
    private List<Meditasyonlar>meditasyonList;
    private Context mContext;

    public HomeCardAdapter(List<Meditasyonlar> meditasyonList, Context mContext) {
        this.meditasyonList = meditasyonList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdapterTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.cardview_home,parent,false);
        return new AdapterTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTutucu holder, int position) {
        Meditasyonlar m=meditasyonList.get(position);
        holder.textViewBaslik.setText(m.getBaslik());
        holder.textViewAciklama.setText(m.getAciklama());
        Picasso.get().load("http://mistikyol.com/mistikmobil/thumbnails/"+m.getThumbnail()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, DetayActivity.class);
                intent.putExtra("nesne",m);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meditasyonList.size();
    }

    public class AdapterTutucu extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView;
        private TextView textViewBaslik,textViewAciklama;
        public AdapterTutucu(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card);
            imageView=itemView.findViewById(R.id.imageViewCard);
            textViewBaslik=itemView.findViewById(R.id.textViewCardBaslik);
            textViewAciklama=itemView.findViewById(R.id.textCardAciklama);
        }
    }
}
