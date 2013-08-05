package com.laifu.livecolorful.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.laifu.livecolorful.R;

public class MyRountListAdapter extends BaseAdapter {
	Context mContext;
	LayoutInflater inflater;
	String[] mFrom;
	int[] mTo;
	List<Map<String, Object>> dataList;
	private View[] itemViews;

	public MyRountListAdapter(Context context, List<Map<String, Object>> mlist,
			String[] str, int[] ids) {
		this.mContext = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mFrom = str;
		mTo = ids;
		dataList = mlist;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dataList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public void setViewText(TextView v, String text) {
		v.setText(text);
	}

	public void setViewImage(ImageView v, String value) {
		try {
			v.setImageResource(Integer.parseInt(value));
		} catch (NumberFormatException nfe) {
			v.setImageURI(Uri.parse(value));
		}
	}

	public void setViewImage(ImageView v, int value) {
		v.setImageResource(value);
	}

	private void bindView(int position, View view) {
		final Map dataSet = dataList.get(position);
		if (dataSet == null) {
			return;
		}

		final String[] from = mFrom;
		final int[] to = mTo;
		final int count = to.length;

		for (int i = 0; i < count; i++) {
			final View v = view.findViewById(to[i]);
			v.setVisibility(android.view.View.VISIBLE);
			if (v != null) {
				final Object data = dataSet.get(from[i]);
				String text = data == null ? "" : data.toString();
				if (text == null) {
					text = "";
				}

				if (v instanceof Checkable) {
					if (data instanceof Boolean) {
						((Checkable) v).setChecked((Boolean) data);
					} else if (v instanceof TextView) {
						// Note: keep the instanceof TextView check at the
						// bottom of these
						// ifs since a lot of views are TextViews (e.g.
						// CheckBoxes).
						setViewText((TextView) v, text);
					} else {
						throw new IllegalStateException(v.getClass().getName()
								+ " should be bound to a Boolean, not a "
								+ (data == null ? "<unknown type>"
										: data.getClass()));
					}
				} else if (v instanceof TextView) {
					// Note: keep the instanceof TextView check at the bottom of
					// these
					// ifs since a lot of views are TextViews (e.g. CheckBoxes).
					setViewText((TextView) v, text);
				} else if (v instanceof ImageView) {
					if (data instanceof Integer) {
						setViewImage((ImageView) v, (Integer) data);
					} else {
						setViewImage((ImageView) v, text);
					}
				} else {
					throw new IllegalStateException(v.getClass().getName()
							+ " is not a "
							+ " view that can be bounds by this SimpleAdapter");
				}

			}
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v;
		if (convertView == null) {
			v = inflater.inflate(R.layout.my_rount_list_adapter, parent, false);
		} else {
			v = convertView;
		}

		bindView(position, v);

		return v;
	}

}
