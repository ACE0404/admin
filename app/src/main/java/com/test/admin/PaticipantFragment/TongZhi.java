package com.test.admin.PaticipantFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.admin.Participant.ImformViewActivity;
import com.test.admin.R;
import com.test.admin.activity.RefreshableView;
import com.test.admin.adapter.ImformationAdapter;
import com.test.admin.bean.AsImformation;
import com.test.admin.bean.AsPermissionApplying;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.test.admin.bean.Parameters.staticObjectdId;
import static com.test.admin.model.Function.showToast;

/**
 * Created by hc6 on 2016/11/16.
 */

public class TongZhi extends Fragment {

    private ListView lv_imformation;
    private ImformationAdapter myImformationAdapter;
    private List<AsImformation> asImformationList = new ArrayList<AsImformation>();
    private RefreshableView refreshableView;

    public TongZhi() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.com_activity_no_find, container, false);

        lv_imformation = (ListView)view.findViewById(R.id.itemList);
        refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);

        BmobQuery<AsImformation> bmobQuery = new BmobQuery<AsImformation>();
        bmobQuery.addQueryKeys("objectId,imTitle,imContent");
        bmobQuery.findObjects(new FindListener<AsImformation>() {
            @Override
            public void done(List<AsImformation> list, BmobException e) {

                if (e == null) {
                    asImformationList.addAll(list);

                    myImformationAdapter = new ImformationAdapter(getActivity(),asImformationList);

                    lv_imformation.setAdapter(myImformationAdapter);
                }
            }
        });

        lv_imformation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ImformViewActivity.class));

                AsImformation asImformation  = asImformationList.get(position);
                staticObjectdId = asImformation.getObjectId().toString();
            }
        });

        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {

                try{
                    Thread.sleep(2000);
                    BmobQuery<AsImformation> bmobQuery = new BmobQuery<AsImformation>();
                    bmobQuery.addQueryKeys("objectId,imTitle,imContent");
                    bmobQuery.findObjects(new FindListener<AsImformation>() {
                        @Override
                        public void done(List<AsImformation> list, BmobException e) {

                            if (e == null) {
                                asImformationList.clear();
                                asImformationList.addAll(list);
                                myImformationAdapter.notifyDataSetChanged();

                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        },3);
        return view;
    }

}