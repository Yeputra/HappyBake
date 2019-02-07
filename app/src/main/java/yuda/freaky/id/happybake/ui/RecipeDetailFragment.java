package yuda.freaky.id.happybake.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yuda.freaky.id.happybake.R;
import yuda.freaky.id.happybake.adapters.RecipeDetailAdapter;
import yuda.freaky.id.happybake.model.Ingredient;
import yuda.freaky.id.happybake.model.Recipe;
import yuda.freaky.id.happybake.widget.UpdateBakingService;

import static yuda.freaky.id.happybake.ui.RecipeDetailActivity.SELECTED_RECIPES;



public class RecipeDetailFragment extends Fragment  {

    ArrayList<Recipe> recipe;
    String recipeName;

    public RecipeDetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView;
        TextView textView;

        recipe = new ArrayList<>();


        if(savedInstanceState != null) {
            recipe = savedInstanceState.getParcelableArrayList(SELECTED_RECIPES);

        }
        else {
            recipe =getArguments().getParcelableArrayList(SELECTED_RECIPES);
        }

        List<Ingredient> ingredients = recipe.get(0).getIngredients();
        recipeName=recipe.get(0).getName();

        View rootView = inflater.inflate(R.layout.recipe_detail_fragment_body_part, container, false);
        textView = (TextView)rootView.findViewById(R.id.recipe_detail_text);

        ArrayList<String> recipeIngredientsForWidgets= new ArrayList<>();


        ingredients.forEach((a) ->
        {
            textView.append("\u2022 "+ a.getIngredient()+"\n");
            textView.append("\t\t\t Quantity: "+a.getQuantity().toString()+"\n");
            textView.append("\t\t\t Measure: "+a.getMeasure()+"\n\n");

            recipeIngredientsForWidgets.add(a.getIngredient()+"\n"+
                    "Quantity: "+a.getQuantity().toString()+"\n"+
                    "Measure: "+a.getMeasure()+"\n");
        });

        recyclerView=(RecyclerView)rootView.findViewById(R.id.recipe_detail_recycler);
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        RecipeDetailAdapter mRecipeDetailAdapter =new RecipeDetailAdapter((RecipeDetailActivity)getActivity());
        recyclerView.setAdapter(mRecipeDetailAdapter);
        mRecipeDetailAdapter.setMasterRecipeData(recipe,getContext());

        //update widget
        UpdateBakingService.startBakingService(getContext(),recipeIngredientsForWidgets);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(SELECTED_RECIPES, recipe);
        currentState.putString("Title",recipeName);
    }


}