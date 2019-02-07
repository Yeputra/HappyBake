package yuda.freaky.id.happybake.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import yuda.freaky.id.happybake.R;
import yuda.freaky.id.happybake.model.Recipe;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecyclerViewHolder> {

    ArrayList<Recipe> lRecipes = new ArrayList<>();
    Context mContext;
    final private ListItemClickListener lOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(Recipe clickedItemIndex);
    }

    public RecipeAdapter(ListItemClickListener listener) {
        lOnClickListener =listener;
    }


    public void setRecipeData(ArrayList<Recipe> recipesIn, Context context) {
        lRecipes = recipesIn;
        mContext=context;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_cardview_items;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup,  false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.textRecyclerView.setText(lRecipes.get(position).getName());
        String imageUrl=lRecipes.get(position).getImage();

            Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
            Picasso.with(mContext)
                    .load(builtUri)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.imageRecyclerView);


    }

    @Override
    public int getItemCount() {
        return lRecipes !=null ? lRecipes.size():0 ;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textRecyclerView;
        ImageView imageRecyclerView;


        public RecyclerViewHolder(View itemView) {
            super(itemView);

            textRecyclerView = (TextView) itemView.findViewById(R.id.title);
            imageRecyclerView = (ImageView) itemView.findViewById(R.id.recipeImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            lOnClickListener.onListItemClick(lRecipes.get(clickedPosition));
        }

    }
}
