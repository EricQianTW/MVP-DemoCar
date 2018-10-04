package com.clown.wyxc.x_message.messagedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.MsgPushMessage;
import com.clown.wyxc.x_message.MessageActivity;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CZP on 2016/7/19.
 */
public class MessageDetailFragment extends BaseFragment implements MessageDetailContract.View {


    @Bind(R.id.recycl_msg)
    RecyclerView recyclMsg;
    private RecyclerAdapter<MsgPushMessage> adapter;
    private MessageDetailContract.Presenter mPresenter;

    @Override
    public void setPresenter(MessageDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_detail_frag, container, false);
        ButterKnife.bind(this, view);
        try {

            mPresenter = new MessageDetailPresenter(this);
            initAdapter();
            initView();
            MessageActivity activity = (MessageActivity) getActivity();
//            mPresenter.getMsgListInfo(user.getVerify(), user.getUserId(), 1, activity.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
            return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static MessageDetailFragment newInstance() {
        MessageDetailFragment fragment = new MessageDetailFragment();
        return fragment;
    }

    public void initView(){
        recyclMsg.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclMsg.setAdapter(adapter);
    }

    public void initAdapter(){
        adapter = new RecyclerAdapter<MsgPushMessage>(getActivity(),R.layout.msgdetail_item) {
            @Override
            protected void convert(RecyclerAdapterHelper helper, MsgPushMessage item) {
                helper.setText(R.id.tv_time,item.getSendTime());
                helper.setText(R.id.tv_title,item.getTitle());
                helper.setText(R.id.tv_detail,item.getContent());
                helper.setOnClickListener(R.id.rl_watchdetail, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        };
    }

    @Override
    public void setMsgListInfoResult(List<MsgPushMessage> list) {
        if(list!=null){
            adapter.replaceAll(list);
        }
    }
}
