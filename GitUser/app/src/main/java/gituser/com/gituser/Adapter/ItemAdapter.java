package gituser.com.gituser.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import gituser.com.gituser.Model.Items;
import gituser.com.gituser.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Items> itemsList;

    public ItemAdapter(List<Items> itemsList) {
        this.itemsList = itemsList;
    }


    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_result, parent, false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        Items listItems = itemsList.get(position);
        holder.mTitle.setText(listItems.getItem_name());


        if (listItems.getItem_avatar() != null) {
            Picasso.get()
                    .load(listItems.getItem_avatar())
                    .resize(150, 150)
                    .centerCrop()
                    .into(holder.mImage);
        }

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;
        ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            mImage = (ImageView) itemView.findViewById(R.id.itemImg);

        }

        @Override
        public void onClick(View v) {

        }
    }
}

