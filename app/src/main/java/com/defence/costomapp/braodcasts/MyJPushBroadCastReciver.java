package com.defence.costomapp.braodcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.defence.costomapp.activity.MainActivity;

import cn.jpush.android.api.JPushInterface;

public class MyJPushBroadCastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
//            Bundle bundle = intent.getExtras();
//            JpushLogger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
//
//            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
//                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//                JpushLogger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
//                //send the Registration Id to your server...
//
//            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//                JpushLogger.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//                processCustomMessage(context, bundle);
//
//            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//                JpushLogger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
//                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//                JpushLogger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//
//            } else

            if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Intent startActivity = new Intent(context, MainActivity.class);
                startActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                for (String key : bundle.keySet()) {
//                    if (key.equals(JPushInterface.EXTRA_EXTRA)) {
//                        String str = (String) bundle.get(key);
//                        Map map = gson.fromJson(str, Map.class);
//                        String command = (String) map.get("command");
////                        PUSH_N_TOPIC_TEST  测试推送,无需任何操作
////                        PUSH_N_TOPIC_HELP_NEW  附近有人发出求助,跳转到 <求助>
////                        PUSH_N_TOPIC_COMMENT  有人回复,跳转到 <回复我的>
////                        PUSH_N_ORDER_EXPRESS_SEND  订单发货,跳转到 <订单详情>
////                        PUSH_N_ORDER_MONEY_RETURN  订单已被商户确认退款,跳转到 <订单详情>
////                        PUSH_N_LUCKY_NEW_RED  红包,操作和打开APP时请求是否有红包、免费商品、新版本一致
////                        PUSH_N_LUCKY_NEW_FREE  免费商品,操作同上
//                        if ("PUSH_N_TEST".equals(command)) {
//                            //测试推送,无需任何操作
//                        } else if ("PUSH_N_TOPIC_HELP_NEW".equals(command)) {
//                            //附近有人发出求助,跳转到 <求助>
//                            startActivity.putExtra("pushType", "PUSH_N_TOPIC_HELP_NEW");
//                        } else if ("PUSH_N_TOPIC_COMMENT".equals(command)) {
//                            //有人回复,跳转到 <回复我的>
//                            startActivity.putExtra("pushType", "PUSH_N_TOPIC_COMMENT");
//                        } else if ("PUSH_N_ORDER_EXPRESS_SEND".equals(command)) {
//                            //订单发货,跳转到 <订单详情>
//                            startActivity.putExtra("pushType", "PUSH_N_ORDER_EXPRESS_SEND");
//                        } else if ("PUSH_N_ORDER_MONEY_RETURN".equals(command)) {
//                            //订单已被商户确认退款,跳转到 <订单详情>
//                            startActivity.putExtra("pushType", "PUSH_N_ORDER_MONEY_RETURN");
//                        } else if ("PUSH_N_LUCKY_NEW_RED".equals(command)) {
//                            //红包,操作和打开APP时请求是否有红包、免费商品、新版本一致
//                            startActivity.putExtra("pushType", "PUSH_N_LUCKY_NEW_RED");
//                        } else if ("PUSH_N_LUCKY_NEW_FREE".equals(command)) {
//                            //免费商品,操作同上
//                            startActivity.putExtra("pushType", "PUSH_N_LUCKY_NEW_FREE");
//                        }
//                    }
//                }
                context.startActivity(startActivity);
                //打开自定义的Activity
//                Intent i = new Intent(context, TestActivity.class);
//                i.putExtras(bundle);
//                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(i);

            }
//            else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
//                JpushLogger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
//                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
//
//            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
//                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
//                JpushLogger.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
//            } else {
//                JpushLogger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
