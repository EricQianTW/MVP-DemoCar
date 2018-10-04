package com.clown.wyxc.x_companydetail.goodsassess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.photo.PhotoActivity;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.EvaluatePic;
import com.clown.wyxc.x_bean.MerchantEvaluateResult;
import com.clown.wyxc.x_bean.x_parambean.MerchantEvaluateQuery;
import com.clown.wyxc.x_companydetail.CompanyDetailActivity;
import com.clown.wyxc.x_companyservices.CompanyServicesActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailFragment_Assess extends BaseFragment implements GoodsDetailContract_Assess.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.tv_services)
    TextView tvServices;
    @Bind(R.id.tv_commercial)
    TextView tvCommercial;
    private GoodsDetailContract_Assess.Presenter mPresenter;
    private RecyclerAdapter<MerchantEvaluateResult> adapter;
    private List<MerchantEvaluateResult> commentInfoList = new ArrayList<>();
    private int goodsId;
    private int currentpage = 1;
    private boolean loadflag = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsdetail_frg_assess, container, false);
        ButterKnife.bind(this, view);

        try {
            initAdapter();
            initView();
            initAction();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initAction() throws Exception {
        tvServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> map = new HashMap<>();
                map.put(CompanyDetailActivity.INTENTNAME_COMPANYID,String.valueOf(goodsId));
                IntentUtils.startActivity(getContext(), CompanyServicesActivity.class,map);
            }
        });

        rvIcon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isSlideToBottom(recyclerView, dy)) {
                    if (!loadflag) {
                        loadflag = true;
                        mPresenter.getMerchantEvaluate(GSONUtils.paramToJson(new MerchantEvaluateQuery(goodsId, currentpage, user.getId())));
                    }
                }
            }

            protected boolean isSlideToBottom(RecyclerView recyclerView, int dy) {
                if (recyclerView == null) return false;
                if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange() && dy > 0)
                    return true;
                return false;
            }
        });
    }

    public GoodsDetailFragment_Assess() {

    }

    public static GoodsDetailFragment_Assess newInstance() {
        return new GoodsDetailFragment_Assess();
    }

    @Override
    public void setPresenter(GoodsDetailContract_Assess.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initAdapter() throws Exception {

        adapter = new RecyclerAdapter<MerchantEvaluateResult>(getContext(), R.layout.goodsdetail_adp_assess) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MerchantEvaluateResult info) {
                try {
                    final int position = helper.getAdapterPosition();
                    if (info.getUserHeadPic() == null || "".equals(info.getUserHeadPic())) {
                        helper.setImageResource(R.id.userheader, R.drawable.user_headpic);
                    } else {
                        helper.setImageUrl(R.id.userheader, info.getUserHeadPic());
                    }
                    helper.setText(R.id.content, info.getDetail())
                            .setText(R.id.username, info.getPhone())
                            .setText(R.id.assessdate, info.getInsDateTime());


                    final BaseAdapter commentAdapter = new BaseListAdapter<EvaluatePic>(getActivity()
                            , info.getEvaluatePicList()) {

                        @Override
                        public View bindView(int position, View convertView, ViewGroup parent) {
                            convertView = null;
                            try {
                                EvaluatePic images = (EvaluatePic) list.get(position);
                                if (convertView == null) {
                                    convertView = createViewByType();
                                }
                                setData(images, convertView, position);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return convertView;
                        }

                        private View createViewByType() {
                            return mInflater.inflate(R.layout.goodsdetail_adp_comment, null);
                        }

                        private void setData(final EvaluatePic item, View convertView, final int position) {
                            ImageView commentpic = ViewHolder.get(convertView, R.id.item_commentpic);
                            ImageLoader.getInstance().displayImage(item.getPic(), commentpic);
                            commentpic.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), PhotoActivity.class);
                                    ArrayList<String> strarray = new ArrayList<String>();
                                    List<EvaluatePic> temp = new ArrayList<EvaluatePic>();
                                    temp = info.getEvaluatePicList();
                                    for (int i = 0; i < info.getEvaluatePicList().size(); i++) {
                                        strarray.add(info.getEvaluatePicList().get(i).getPic());
                                    }
                                    intent.putStringArrayListExtra(com.clown.wyxc.scheme.Constants.JUMP_STRING, strarray);
                                    intent.putExtra(com.clown.wyxc.scheme.Constants.JUMP_STRING1, position);
                                    startActivity(intent);
                                }
                            });
                        }

                    };

                    if (commentAdapter != null) {
                        helper.setAdapter(R.id.contentpic, commentAdapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
    }

    public void initView() throws Exception {
        Bundle data = getArguments();
        goodsId = data.getInt(CompanyDetailActivity.INTENTNAME_COMPANYID);

        mPresenter.getMerchantEvaluate(GSONUtils.paramToJson(new MerchantEvaluateQuery(goodsId, currentpage, user.getId())));

        rvIcon.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.setAdapter(adapter);
    }

    @Override
    public void setGetMerchantEvaluateResult(List<MerchantEvaluateResult> result) {
        if (result != null) {
            loadflag = false;
            commentInfoList.addAll(result);
            currentpage++;
            adapter.replaceAll(commentInfoList);
        }
    }
}
