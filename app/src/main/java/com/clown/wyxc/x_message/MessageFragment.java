package com.clown.wyxc.x_message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.MessageResult;
import com.clown.wyxc.x_bean.x_parambean.MessageQuery;
import com.clown.wyxc.x_message.messagedetail.MessageDetailFragment;
import com.clown.wyxc.x_utils.Router;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by CZP on 2016/7/19.
 */
public class MessageFragment extends BaseFragment implements MessageContract.View {
    @Bind(R.id.rv_msg)
    RecyclerView rvMsg;
    @Bind(R.id.ll_main)
    FrameLayout llMain;
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;
    private MessageContract.Presenter mPresenter;
    private RecyclerAdapter<MessageResult> adapter;

    private int pageIndex = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_frag, container, false);
        ButterKnife.bind(this, view);
        try {
            initAdapter();
            initView();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public MessageFragment() {
        new MessagePresenter(this);
    }

    @Override
    public void setPresenter(MessageContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public void replacefrag(int i) throws Exception {
        MessageActivity activity = (MessageActivity) getActivity();
        activity.setType(i);
        ActivityUtils.replaceFragmentFromActivity(getActivity().getSupportFragmentManager()
                , R.id.contentfrag, new MessageDetailFragment());

    }

    public void initView() {
        rvMsg.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvMsg.setAdapter(adapter);
    }

    public void initAdapter() {
        adapter = new RecyclerAdapter<MessageResult>(getActivity(), R.layout.message_item) {
            @Override
            protected void convert(RecyclerAdapterHelper helper, final MessageResult item) {
                helper.setImageUrl(R.id.iv_pic, item.getIconUrl());
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_time, item.getSendTime());
                helper.setText(R.id.tv_content, item.getContent());
                helper.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Router.startActivty(getActivity(), item.getClickUrl(), false);
                    }
                });
            }
        };
    }

    public void initData() throws Exception {
        mPresenter.getMessageList(GSONUtils.paramToJson(new MessageQuery(user.getId(), pageIndex)));
    }

    @Override
    public void setgetMessageListResult(List<MessageResult> result) {
        if (result.size() > 0) {
            adapter.addAll(result);
            ivEmpty.setVisibility(View.INVISIBLE);
            pageIndex++;
        } else {

        }
    }
}
