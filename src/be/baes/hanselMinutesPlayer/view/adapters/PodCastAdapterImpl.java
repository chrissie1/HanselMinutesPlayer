package be.baes.hanselMinutesPlayer.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.resources.ColorResources;
import be.baes.hanselMinutesPlayer.resources.StringResources;

import java.io.File;
import java.util.List;

public class PodCastAdapterImpl extends ArrayAdapter<PodCast>
{
    private	int resource;
    private StringResources stringResources;
    private ColorResources colorResources;
    private Settings settings;

	public PodCastAdapterImpl(Context context, int resource, List<PodCast> items, Settings settings, StringResources stringResources1, ColorResources colorResources1)
	{
        super(context, resource, items);
        this.stringResources = stringResources1;
        this.colorResources = colorResources1;
        this.settings = settings;
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

        File downloadedFile = new File(settings.getCacheDirectory(), podCast.getPodCastName());
        if (downloadedFile.exists()) {
            rowTitle.setTextColor(colorResources.getColorGreen());
        }
        else
        {
            //rowTitle.setTextColor(colorResources.getColorWhite());
        }
        rowTitle.setText(String.format(stringResources.getListViewTitleText(), position + 1, podCast.getTitle()));
        rowPubDate.setText(podCast.getPubDate());
		return rssItemView;
	}

}
