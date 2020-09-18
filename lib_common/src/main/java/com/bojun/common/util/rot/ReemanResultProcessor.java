package com.bojun.common.util.rot;

import android.content.Context;

import com.speech.abstracts.IResultProcessor;
import com.speech.bean.ReemanResult;
import com.speech.processor.SpeechPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 简  述
 * 作  者  chenxiaoping
 * 包  名  com.bojun.common.util.rot
 * 时  间  2020/9/18 18:08
 */
public class ReemanResultProcessor implements IResultProcessor {

    private static final String TAG = "BjResultProcessor";
    private Context mContext;
    private int unKnowNum = 0;
    private int unKnowNumMax = 3;

    public ReemanResultProcessor(Context context) {
        mContext = context;
//        if (homeView != null) {
//            mView = homeView;
//        }
    }

    @Override
    public void onPartialResult(ReemanResult reemanResult) {
        if (reemanResult == null ) {
            return;
        }
        String json = reemanResult.getJson();
        int type = reemanResult.getType();
        if (json == null) {
            List<String> list = Arrays.asList(Constants.NO_ANSWER_SAY);
            Collections.shuffle(list);
            SpeechPlugin.getInstance().startSpeak(list.get(0));
            return;
        }
        //TODO 导航过程中被打断问题  监控有效状态
        if (LocalConfig.AutoIntroduce == 0 && LocalConfig.isAutoNavRuning && !LocalConfig.isPlayVideo) {
            RobotHardSdk hardSdk = RobotHardSdk.getInstance();
            if (hardSdk != null) {
                hardSdk.reStartCountDownTimer();
            }
        }

        switch (type) {
            case 1:
                // reeman
                handleReemanJson(reemanResult);
                break;
            case 2:
                // ifly
                if (!IntroduceManager.getInstance().judgeIfSpeech()) {
                    return;
                }
                handleIflyJson(reemanResult);
                break;
            default:
                break;
        }
    }

    private void handleIflyJson(ReemanResult result) {
//        String json = result.getJson();
//        if (TextUtils.isEmpty(json)) {
//            if (mView != null) {
//                mView.displayNoAnswer(Constants.NO_ANSWER_SAY);
//            }
//        } else {
//            NLPResult mResult = new NLPResult(json);
//            String iflyAnswer = mResult.getmAnswer();
//            String rawText = mResult.getmRawtext();
//            String focus = mResult.getmFocus();
//            //呼叫名字应答
//            for (String name : Constants.ROBOT_NAMES) {
//                if (rawText.contains(name)) {
//                    List<String> robotResponse = Arrays.asList(Constants.ROBOT_RESPONSE);
//                    Collections.shuffle(robotResponse);
//                    SpeechPlugin.getInstance().startSpeak(robotResponse.get(0));
//                    mView.displayAnswerText(robotResponse.get(0));
//                    return;
//                }
//            }
//
//            //TODO 应该使用正则表达式
//            //被询问名字
//            if ((rawText.contains("你") && rawText.contains("名字")) || rawText.contains("你好") || rawText.contains("你是谁")) {
//                String answer = "你好，我叫" + Constants.RONAME;
//                SpeechPlugin.getInstance().startSpeak(answer);
//                RobotActionProvider.getInstance().combinedActionTtyS4(1);
//                mView.displayAnswerText(answer);
//                return;
//            }
//
//
//
//            //TODO 应该使用正则表达式
//            //导航迷失方向时，通过语音命令纠正
//            if (rawText.contains("你迷路") || rawText.contains("你走错") || rawText.contains("你走偏") || rawText.contains("你跑偏")) {
//                //全局重定位，有一定概率匹配错误，无法找回正确位置
//                RobotActionProvider.getInstance().sendRosCom("nav:reloc");
//                SpeechPlugin.getInstance().startSpeak("人家正在睁大眼睛看着呢");
//                return;
//            }
//
//            //当机器人彻底迷失后，将机器人带到提前设定好的"重定位点"后，执行该命令让机器人重新找回位置
//            if (rawText.contains("机器人重定位")) {
//                NavigationManager.goNavigation("重定位点", "已执行重定位");
//                return;
//            }
//
//            //执行语音命令去充电
//            if (rawText.contains("充电")) {
//                NavigationManager.goNavigation("充电站", null);
//                return;
//            }
//
//            //判断是否需要机器人带路
//            if (rawText.contains("带") || rawText.contains("领") || rawText.startsWith("去")) {
//                String goal;
////                mView.displayAnswerText(rawText);
////                mView.toasts(rawText);
//                if (rawText.contains("去")) {
//                    goal = rawText.substring(rawText.indexOf("去") + 1);
//                } else if (rawText.contains("到")) {
//                    goal = rawText.substring(rawText.indexOf("到") + 1);
//                } else if (rawText.contains("回")) {
//                    goal = rawText.substring(rawText.indexOf("回") + 1);
//                } else {
//                    return;
//                }
//                NavigationManager.goNavigation(goal, null);
//                return;
//            }
//
//            if ("openQA".equals(focus)) {
//                try {
//                    if (TextUtils.isEmpty(iflyAnswer)) {
//                        //mViewControl.setRobot("还听不懂呢");
//                        if (mView != null) {
//                            mView.displayNoAnswer(Constants.NO_ANSWER_SAY);
//                        }
//                        unKnowNum++;
//                        if (unKnowNum == unKnowNumMax) {
//                            //mViewControl.handleTipsText();
//                            unKnowNum = 0;
//                        }
//                    } else {
//                        unKnowNum = 0;
//                        if (NLPResult.RAWTEXT_WEATHER_LOCAL.equals(iflyAnswer)) {
//                            SpeechPlugin.getInstance().onIflyTextUnderstand(BjApplication.curLocation + rawText);
//                            return;
//                        }
//                        SpeechPlugin.getInstance().startSpeak(iflyAnswer);
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            } else if ("weather".equals(focus)) {
//                try {
//                    if (TextUtils.isEmpty(iflyAnswer)) {
////                        mViewControl.setRobot("还听不懂呢");
//                        if (mView != null) {
//                            mView.displayNoAnswer(Constants.NO_ANSWER_SAY);
//                        }
//                        unKnowNum++;
//                        if (unKnowNum == unKnowNumMax) {
////                    mViewControl.handleTipsText();
//                            unKnowNum = 0;
//                        }
//                    } else {
//                        unKnowNum = 0;
//                        if (NLPResult.RAWTEXT_WEATHER_LOCAL.equals(iflyAnswer)) {
//                            SpeechPlugin.getInstance().onIflyTextUnderstand(BjApplication.curLocation + rawText);
//                            return;
//                        }
//                        SpeechPlugin.getInstance().startSpeak(iflyAnswer);
//                        if (mView != null) {
//                            mView.displayAnswerText(iflyAnswer);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                SpeechPlugin.getInstance().startSpeak(iflyAnswer);
//                if (iflyAnswer.equals("")) {
//                    if (mView != null) {
//                        mView.displayNoAnswer(Constants.NO_ANSWER_SAY);
//                    }
//                } else {
//                    if (mView != null) {
//                        mView.displayAnswerText(iflyAnswer);
//                    }
//                }
//            }
//        }
    }

    //处理来自资料库中的结果
    private void handleReemanJson(ReemanResult result) {
//        try {
//            if (result == null) {
//                return;
//            }
//            Log.d(TAG, "handleReemanJson() called with: result = [" + result.getJson() + "]");
//            String json = result.getJson();
//            if (json == null || json.length() < 1) {
//                return;
//            }
//            JSONObject root = new JSONObject(json);
//            final String answer = root.optString("Data");
//            if (answer == null || answer.length() < 1) {
//                return;
//            }
//            Log.d(TAG, "handleReemanJson() called with: answer------> = [" + answer + "]");
//            unKnowNum = 0;
//            if (!answer.startsWith("_") && !IntroduceManager.getInstance().judgeIfSpeech()) {
//                return;
//            }
//            if (answer.contains("start_show")) {
//                StartShowStrategy startShowStrategy = new StartShowStrategy();
//                NluStrategyContext strategyContext = new NluStrategyContext(startShowStrategy);
//                strategyContext.handleJson(mContext, answer, mView);
//            } else if (answer.contains("navigation_")) {
//                NavigationStrategy navigationStrategy = new NavigationStrategy();
//                NluStrategyContext strategyContext = new NluStrategyContext(navigationStrategy);
//                strategyContext.handleJson(mContext, answer, mView);
//            } else if (answer.contains("charge_")) {
//                ChargingStrategy chargingStrategy = new ChargingStrategy();
//                NluStrategyContext strategyContext = new NluStrategyContext(chargingStrategy);
//                strategyContext.handleJson(mContext, answer, mView);
//            } else if (answer.contains("scheduling_")) {
//                SchedulingStrategy schedulingStrategy = new SchedulingStrategy();
//                NluStrategyContext strategyContext = new NluStrategyContext(schedulingStrategy);
//                strategyContext.handleJson(mContext, answer, mView);
//            } else if (answer.contains("json_")) {
//                JsonStrategy jsonStrategy = new JsonStrategy();
//                NluStrategyContext strategyContext = new NluStrategyContext(jsonStrategy);
//                strategyContext.handleJson(mContext, answer, mView);
//            } else if (answer.contains("continue_visit")) {
//                ContinueVisitStrategy continueVisitStrategy = new ContinueVisitStrategy();
//                NluStrategyContext strategyContext = new NluStrategyContext(continueVisitStrategy);
//                strategyContext.handleJson(mContext, answer, mView);
//            } else {
//                //直接说出答案
//                SpeechPlugin.getInstance().startSpeak(answer);
//                TextAnswerStrategy textAnswerStrategy = new TextAnswerStrategy();
//                NluStrategyContext strategyContext = new NluStrategyContext(textAnswerStrategy);
//                strategyContext.handleJson(mContext, answer, mView);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}