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
import com.clown.wyxc.x_bean.VehiclBrand;

import java.util.ArrayList;
import java.util.List;

public class VehiclBrandView extends RelativeLayout implements ViewBaseAction {

    private ListView mListView;
    private OnSelectListener mOnSelectListener;
    private TextAdapter adapter;
    private String showText = "item1";
    private List<VehiclBrand> mArray;
    private View mView;

    public String getShowText() {
        return showText;
    }

    public VehiclBrandView(Context context, List<VehiclBrand> array) {
        super(context);
        init(context, array);
    }

    public VehiclBrandView(Context context, AttributeSet attrs, int defStyle, List<VehiclBrand> array) {
        super(context, attrs, defStyle);
        init(context, array);
    }

    public VehiclBrandView(Context context, AttributeSet attrs, List<VehiclBrand> array) {
        super(context, attrs);
        init(context, array);
    }

    private void init(Context context, List<VehiclBrand> array) {
        mArray = array;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.view_distance, this, true);
        setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_right));
        mListView = (ListView) findViewById(R.id.listView);
        List<String> arrName = new ArrayList<>();
        for (VehiclBrand vehiclBrand : array) {
            arrName.add(vehiclBrand.getName());
        }
        adapter = new TextAdapter(context, arrName, R.drawable.choose_item_right, R.drawable.choose_eara_item_selector);
        adapter.setTextSize(17);
//        if (mDistance != null) {
//            for (int i = 0; i < mArray.size(); i++) {
//                if (String.valueOf(mArray.get(i).getId()).equals(mDistance)) {
//                    adapter.setSelectedPositionNoNotify(i);
//                    showText = mArray.get(i).getName();
//                    break;
//                }
//            }
//        }
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
        public void getValue(VehiclBrand distance);
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {

    }

}
