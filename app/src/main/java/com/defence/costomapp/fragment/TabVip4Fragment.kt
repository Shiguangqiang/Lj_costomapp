package com.defence.costomapp.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.Toast

import com.defence.costomapp.R
import com.defence.costomapp.adapter.MachineRegisAdapter
import com.defence.costomapp.base.BaseFragment
import com.defence.costomapp.base.Urls
import com.defence.costomapp.bean.MachineStaticsBean
import com.defence.costomapp.utils.RefreshUtils.RefreshLayout
import com.defence.costomapp.utils.SgqUtils
import com.defence.costomapp.utils.httputils.HttpInterface
import com.google.gson.Gson
import com.loopj.android.http.RequestParams
import org.json.JSONException
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TabVip4Fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TabVip4Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabVip4Fragment : BaseFragment() {

    private var lv_machineRegistration: ListView? = null
    private var srl: RefreshLayout? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_tab_vip4, container, false)

        lv_machineRegistration = view.findViewById<ListView>(R.id.lv_machineRegistration)



        srl = view.findViewById<RefreshLayout>(R.id.srl)


        srl!!.setOnLoadListener {
            length++
            //注册机器统计
            getMachineData()

        }

        srl!!.setOnRefreshListener {
            length = 0
            if (machinestaticlist != null) {
                machinestaticlist!!.clear()
            }
            //注册机器统计
            getMachineData()

        }

        //注册机器统计
        getMachineData()


//        val machineRegisAdapter = MachineRegisAdapter(arrayList, context)
//        lv_machineRegistration.adapter = machineRegisAdapter

        return view
    }


    private var length = 0
    private var machineRegisAdapter: BaseAdapter? = null
    private var machinestaticlist: MutableList<MachineStaticsBean>? = null

    /*机器注册统计*/
    private fun getMachineData() {
        val params = RequestParams()
        params.add("listNum", ((length * 5).toString()))
        params.add("pags", "5")
        httpUtils.doPost(Urls.machineStatics(), SgqUtils.TONGJI_TYPE, params, object : HttpInterface() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Throws(JSONException::class)
            override fun onSuccess(gson: Gson, result: Any, message: String) {

                srl!!.isRefreshing = false
                srl!!.setLoading(false)

                try {
                    if (result != null) {

                        val listBean = gson.fromJson(result.toString(), List::class.java)
                        val machineBean = ArrayList<MachineStaticsBean>()

                        for (obj in listBean) {
                            val allMachineBean = Gson().fromJson<MachineStaticsBean>(obj.toString(), MachineStaticsBean::class.java)
                            machineBean.add(allMachineBean)
                        }

                        if (machinestaticlist == null)
                            machinestaticlist = ArrayList()

                        machinestaticlist!!.addAll(machineBean)

                        if (machineRegisAdapter == null) {
                            machineRegisAdapter = activity?.let { MachineRegisAdapter(machinestaticlist, it) }
                            lv_machineRegistration!!.adapter = machineRegisAdapter
                        } else {
                            machineRegisAdapter!!.notifyDataSetChanged()
                        }


//                        machineRegisAdapter = MachineRegisAdapter(machineBean, activity)
//                        lv_machineRegistration!!.adapter = machineRegisAdapter

//                        val machineRegisAdapter = MachineRegisAdapter2(machineBean, activity)
//                        lv_machineRegistration!!.adapter = machineRegisAdapter


                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

            override fun onError(context: Context?, message: String?) {
                super.onError(context, message)
                srl!!.isRefreshing = false
                srl!!.setLoading(false)
            }


            override fun onFailure(context: Context?) {
                super.onFailure(context)
                srl!!.isRefreshing = false
                srl!!.setLoading(false)

            }
        })


    }

//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//
//
//        if (isVisibleToUser) {
//            getMachineData()
//        }
//        super.setUserVisibleHint(isVisibleToUser)
//    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TabVip4Fragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): TabVip4Fragment {
            val fragment = TabVip4Fragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
