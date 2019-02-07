package yuda.freaky.id.happybake.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {

	private Integer id;
	private String shortDescription;
	private String description;
	private String videoURL;
	private String thumbnailURL;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public String getThumbnailURL() {
		return thumbnailURL;
	}


	protected Steps(Parcel in) {
		id = in.readByte() == 0x00 ? null : in.readInt();
		shortDescription = in.readString();
		description = in.readString();
		videoURL = in.readString();
		thumbnailURL = in.readString();
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
		dest.writeString(shortDescription);
		dest.writeString(description);
		dest.writeString(videoURL);
		dest.writeString(thumbnailURL);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Steps> CREATOR = new Parcelable.Creator<Steps>() {
		@Override
		public Steps createFromParcel(Parcel in) {
			return new Steps(in);
		}

		@Override
		public Steps[] newArray(int size) {
			return new Steps[size];
		}
	};
}