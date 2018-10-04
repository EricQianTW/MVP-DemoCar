package com.clown.wyxc.x_vehicletradelist.tabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.components.expandtabview.ViewBaseAction;
import com.clown.wyxc.components.expandtabview.adapter.TextAdapter;
import com.clown.wyxc.x_bean.VehiclPriceRangeResult;

import java.util.ArrayList;
import java.util.List;

public class VehiclPriceView extends RelativeLayout implements ViewBaseAction {

    private ListView mListView;
    private OnSelectListener mOnSelectListener;
    private TextAdapter adapter;
    private String mDistance;
    private String showText = "item1";
    private Context mContext;
    private View mView;
    private List<VehiclPriceRangeResult> mArray;

    public String getShowText() {
        return showText;
    }

    public VehiclPriceView(Context context, List<VehiclPriceRangeResult> array) {
        super(context);
        init(context, array);
    }

    public VehiclPriceView(Context context, AttributeSet attrs, int defStyle, List<VehiclPriceRangeResult> array) {
        super(context, attrs, defStyle);
        init(context, array);
    }

    public VehiclPriceView(Context context, AttributeSet attrs, List<VehiclPriceRangeResult> array) {
        super(context, attrs);
        init(context, array);
    }

    private void init(Context context, List<VehiclPriceRangeResult> array) {
        mContext = context;
        mArray = array;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.view_distance, this, true);
        setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_right));
        mListView = (ListView) findViewById(R.id.listView);
        List<String> arrName = new ArrayList<>();
        for (VehiclPriceRangeResult vehiclBrand : array) {
            arrName.add(vehiclBrand.getName());
        }
        adapter = new TextAdapter(context, arrName, R.drawable.choose_item_right, R.drawable.choose_eara_item_selector);
        adapter.setTextSize(17);
        if (mDistance != null) {
            for (int i = 0; i < mArray.size(); i++) {
                if (String.valueOf(mArray.get(i).getValue()).equals(mDistance)) {
                    adapter.setSelectedPositionNoNotify(i);
                    showText = mArray.get(i).getName();
                    break;
                }
            }
        }
        mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                if (mOnSelectListener != null) {
                    showText = mArray.get(position).getName();
                    mOnSelectListener.getValue(mArray.get(position));
                }
            }
        });
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        public void getValue(VehiclPriceRangeResult result);
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {

    }

}
