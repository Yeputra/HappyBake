package yuda.freaky.id.happybake.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

	private Integer id;
	private String name;
	private List<Ingredient> ingredients = null;
	private List<Steps> steps = null;
	private Integer servings;
	private String image;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public List<Steps> getSteps() {
		return steps;
	}

	public String getImage() {
		return image;
	}



	protected Recipe(Parcel in) {
		id = in.readByte() == 0x00 ? null : in.readInt();
		name = in.readString();
		if (in.readByte() == 0x01) {
			ingredients = new ArrayList<>();
			in.readList(ingredients, Ingredient.class.getClassLoader());
		} else {
			ingredients = null;
		}
		if (in.readByte() == 0x01) {
			steps = new ArrayList<>();
			in.readList(steps, Steps.class.getClassLoader());
		} else {
			steps = null;
		}
		servings = in.readByte() == 0x00 ? null : in.readInt();
		image = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (id == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeInt(id);
		}
		dest.writeString(name);
		if (ingredients == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(ingredients);
		}
		if (steps == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(steps);
		}
		if (servings == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeInt(servings);
		}
		dest.writeString(image);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
		@Override
		public Recipe createFromParcel(Parcel in) {
			return new Recipe(in);
		}

		@Override
		public Recipe[] newArray(int size) {
			return new Recipe[size];
		}
	};
}