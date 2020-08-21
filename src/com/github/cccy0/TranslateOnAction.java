package com.github.cccy0;

import com.google.gson.Gson;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import org.apache.http.util.TextUtils;

import java.awt.*;

/**
 * @author Zhai
 * 2020/8/20 9:49
 */

public class TranslateOnAction extends AnAction {
    private final Gson gson = new Gson();

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        final Editor data = e.getData(PlatformDataKeys.EDITOR);
        if (data == null) {
            return;
        }
        SelectionModel model = data.getSelectionModel();
        String text = model.getSelectedText();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        System.out.println(text);
        String baseUrl = "http://fanyi.youdao.com/openapi.do?keyfrom=Skykai521&key=977124034&type=data&doctype=json&version=1.1&q=";
        baseUrl = "http://fy.iciba.com/ajax.php?a=fy&f=auto&t=zh&w=";

        HttpUtils.doGetAsync(baseUrl + text, result -> {
            System.out.println(result);
            Translation translation = gson.fromJson(result, Translation.class);
            if (0 == translation.getStatus()) {
                Messages.showInfoMessage(translation.getContent().getWord_mean().toString(), "翻译结果");
            } else {
                Messages.showInfoMessage(translation.getContent().getOut(), "翻译结果");
            }
//            showPopupBalloon(data, translation.toString());
        });
    }

    /**
     * 好像有问题, 先不采用这种方式- -
     * @param editor editor
     * @param result result
     */
    private void showPopupBalloon(final Editor editor, final String result) {
        System.out.println("7777");
        ApplicationManager.getApplication().invokeLater(() -> {
            JBPopupFactory factory = JBPopupFactory.getInstance();
            System.out.println("8888");
            factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
                    .setFadeoutTime(5000)
                    .createBalloon()
                    .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
        });
    }
}
