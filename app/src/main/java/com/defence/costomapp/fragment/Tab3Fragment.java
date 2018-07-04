package com.defence.costomapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.MaciChuNumBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by author Sgq
 * on 2018/3/14.
 */

public class Tab3Fragment extends BaseFragment {


    @BindView(R.id.list_tab3)
    ListView listTab3;
    Unbinder unbinder;
    private String groupid;
    private String uid;
    private String wxid;
    private String mPayType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, null);
        unbinder = ButterKnife.bind(this, view);
        initdata();
        return view;
    }

    private void initdata() {
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        wxid = getActivity().getIntent().getStringExtra("wxid");
        uid = getActivity().getIntent().getStringExtra("uid");
        mPayType = getActivity().getIntent().getStringExtra("payType");
        RequestParams params = new RequestParams();
        params.put("orderUID", uid);
        params.put("wxopenID", wxid);
        params.put("orderBy", "2");
        params.put("payType", mPayType);
        httpUtils.doPost(Urls.macchunum(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                MaciChuNumBean maciChuNumBean = gson.fromJson(jsonObject.toString(), MaciChuNumBean.class);

                try {
                    Tab3Adapter tab2Adapter = new Tab3Adapter(getActivity(), maciChuNumBean.getList());
                    listTab3.setAdapter(tab2Adapter);
                } catch (Exception e) {

                }
            }
        });
    }

    private class Tab3Adapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<MaciChuNumBean.ListBean> list;
        private RVItemClickListener rvItemClickListener;

        public Tab3Adapter(Context context, List<MaciChuNumBean.ListBean> list) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.list = list;


        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (list != null && list.size() > 0) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return list.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup arg2) {
            // TODO Auto-generated method stub
            if (view == null) {
                view = inflater.inflate(R.layout.item_usertjnew, null);
            }
            TextView reg_phone = view.findViewById(R.id.reg_phone);
            TextView tv_yue = view.findViewById(R.id.tv_yue);
            TextView tv_nearlogintime = view.findViewById(R.id.tv_nearlogintime);
            tv_yue.setVisibility(View.GONE);
            reg_phone.setText(list.get(position).getAd());
            tv_nearlogintime.setText("数量:" + list.get(position).getCt());


            return view;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
