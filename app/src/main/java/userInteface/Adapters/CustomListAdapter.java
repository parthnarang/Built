package userInteface.Adapters;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import app.AppController;
import model.Movie;
import model.builtCore.Merchant;
import parth.com.buiit.R;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private ArrayList<Merchant> merchantList;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, ArrayList<Merchant> merchantList) {
		this.activity = activity;
		this.merchantList = merchantList;
	}

	@Override
	public int getCount() {
		Log.d("abc",""+merchantList.size());
		return merchantList.size();
	}

	@Override
	public Object getItem(int location) {
		return merchantList.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    Log.d("abc","sjjdjd");

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		//TextView genre = (TextView) convertView.findViewById(R.id.genre);
		//TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
		Log.d("abc",""+position);

		// getting movie data for the row
		Merchant merchant = merchantList.get(position);

		// thumbnail image
		thumbNail.setImageUrl(merchant.getMerchantLogo(), imageLoader);
		
		// title
		title.setText(merchant.getMerchantName());
		
		// rating
		//rating.setText("Rating: " + String.valueOf(m.getRating()));
		
		// genre
		/*String genreStr = "";
		for (String str : m.getGenre()) {
			genreStr += str + ", ";
		}
		genreStr = genreStr.length() > 0 ? genreStr.substring(0,
				genreStr.length() - 2) : genreStr;
		//genre.setText(genreStr);
		
		// release year
		//year.setText(String.valueOf(m.getYear()));*/

		return convertView;
	}

}