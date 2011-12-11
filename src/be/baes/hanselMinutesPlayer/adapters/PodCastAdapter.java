package be.baes.hanselMinutesPlayer.adapters;

import java.util.List;

import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.rss.RSSItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PodCastAdapter extends ArrayAdapter<PodCast>
{

	int resource;
	String response;
	Context context;

	public PodCastAdapter(Context context, int resource, List<PodCast> items)
	{
		super(context, resource, items);
		this.resource=resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout rssItemView;
        PodCast podCast = getItem(position);

		if(convertView==null)
		{
			rssItemView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi;
			vi = (LayoutInflater)getContext().getSystemService(inflater);
			vi.inflate(resource, rssItemView, true);
		}
		else
		{
			rssItemView = (LinearLayout) convertView;
		}
		TextView rowTitle =(TextView)rssItemView.findViewById(R.id.rowTitle);
		TextView rowPubDate =(TextView)rssItemView.findViewById(R.id.rowPubDate);

		rowTitle.setText((position+1) + ". " + podCast.getTitle());
		rowPubDate.setText(podCast.getPubDate());
		return rssItemView;
	}

}
