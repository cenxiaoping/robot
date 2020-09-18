package com.bojun.common.util.rot;

import com.iflytek.cloud.SpeechError;
import com.speech.abstracts.IRecognizeListener;

/**
 * @author xiaoqqq
 * @package com.ihealthcare.robot.aipower
 * @date 2018/11/6
 * @describe 语音识别回调监听
 */

public class BjRecognizeListener implements IRecognizeListener {
    private static final String TAG = "BjRecognizeListener";
    private static BjRecognizeListener instance = new BjRecognizeListener();
    private int errorMax = 5;
    private int currError = 0;

    private BjRecognizeListener() {
    }


    public static BjRecognizeListener getInstance() {
        return instance;
    }

    @Override
    public void onBeginOfSpeech() {

    }

    @Override
    public void onError(SpeechError speechError) {
        int code = speechError.getErrorCode();
        if (code == 10118) {
            currError = currError + 1;
            if (currError == errorMax) {
//                mViewControl.addReceptionView();
            }
        }
    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onResult(String rawText) {//TODO：一下代码，未经验证。
//        currError = 0;
//        String currentClassName = SystemUtils.getCurrentClassName(BjApplication.getAppContext());
//        // com.ihealthcare.robot.activity.HomeActivity
//        // 只有在HomeActivity中才做语音交互，其他界面不管
//        if ("com.ihealthcare.robot.activity.HomeActivity".equals(currentClassName)) {
//            if (!TextUtils.isEmpty(rawText)) {
//                if (mView != null) {
//                    mView.displayUserAsk(rawText);
//                }
//                SpeechPlugin.getInstance().setChatFlowUrl(NluApis.CONVERSATION_URL);
//                SpeechPlugin.getInstance().setChatFlowLan(rawText);
//                HttpUtil.getInstance().get(NluApis.CONVERSATION_URL + rawText, new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.d(TAG, "onFailure() called with: call = [" + call + "], IOException = [" + e.getMessage() + "]");
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        ResponseBody body = response.body();
//                        if (body != null) {
//                            String json = body.string();
//                            Log.d(TAG, "onResponse() called with: -------------> json = [" + json + "]");
//                            // SpeechPlugin.getInstance().onReemanTextUnderstand();
//                            JsonArray jsonArray = (JsonArray) new JsonParser().parse(json);
//                            if (jsonArray != null && jsonArray.isJsonArray() && jsonArray.size() > 0) {
//                                JsonObject jsonObject = (JsonObject) jsonArray.get(0);
//                                if (jsonObject.has("answer")) {
//                                    JsonObject answer = jsonObject.getAsJsonObject("answer");
//                                    String text = answer.getAsJsonObject("text").toString();
//                                    if (TextUtils.isEmpty(text)) return;
//                                    if (text.contains("您的问题难倒我了")) {
//                                        SpeechPlugin.getInstance().onReemanTextUnderstand(rawText);
//                                        return;
//                                    }
//                                    mView.displayAnswerText(text);
//                                    SpeechPlugin.getInstance().startSpeak(text);
//                                }
//                            }
//
//                        }
//                    }
//                });
//            }
//        }
    }



    @Override
    public void onVolumeChanged(int i, byte[] bytes) {
//        if (mView != null) {
//            mView.setVoice(i);
//        }
    }
}
