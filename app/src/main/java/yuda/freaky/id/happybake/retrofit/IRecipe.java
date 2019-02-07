package yuda.freaky.id.happybake.retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import yuda.freaky.id.happybake.model.Recipe;


public interface IRecipe {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}
