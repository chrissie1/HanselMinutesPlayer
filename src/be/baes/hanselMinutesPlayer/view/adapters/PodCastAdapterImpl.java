package be.baes.hanselMinutesPlayer.view.adapters;

import java.io.File;
import java.util.List;

import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.model.PodCast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PodCastAdapterImpl extends ArrayAdapter<PodCast>
{
    private	int resource;

	public PodCastAdapterImpl(Context context, int resource, List<PodCast> items)
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

        File downloadedFile = new File(getContext().getExternalCacheDir() , podCast.getPodCastName());
        if (downloadedFile.exists()) {
            rowTitle.setTextColor(getContext().getResources().getColor(R.color.green));
        }
        else
        {
            rowTitle.setTextColor(getContext().getResources().getColor(R.color.white));
        }
        rowTitle.setText(String.format("%d. %s", position + 1, podCast.getTitle()));
        rowPubDate.setText(podCast.getPubDate());
		return rssItemView;
	}

}
