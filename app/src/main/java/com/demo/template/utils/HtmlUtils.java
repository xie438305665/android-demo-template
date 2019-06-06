package com.demo.template.utils;

import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.PaperEnglishTopicDetailEntity;
import com.android.library.net.entity.template.TopicDetailEntity;
import com.android.library.net.entity.template.ScoreTaskEntity;

import java.util.List;

/**
 * @author y
 * @create 2018/12/5
 */
public class HtmlUtils {

    public static String getTopicHtml(String text) {
        return "<!DOCTYPE html>"
                + "<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>"
                + "<script src='http://g.tbcdn.cn/mtb/lib-flexible/0.3.2/??flexible_css.js,flexible.js'></script>"
                + "<head><style>*{padding:8;margin:0;}p{display:inline-block;}html{background:#D6DCE4;}</style><meta charset='utf-8'/></head>"
                + "<body>"
                + text
                + "</body>"
                + "</html>";
    }

    public static String getNameHtml(List<ScoreTaskEntity.TeacherMarkingScores> teacherMarkingScores) {
        if (UIUtils.isEmpty(teacherMarkingScores)) {
            return "";
        }
        StringBuilder option = new StringBuilder();
        for (int i = 0; i < teacherMarkingScores.size(); i++) {
            option.append(teacherMarkingScores.get(i).getTeacherName()).append("<font color='#c55555'>").append(teacherMarkingScores.get(i).getScoring()).append("</font>").append("分");
            if (i != teacherMarkingScores.size() - 1) {
                option.append(" ");
            }
        }
        return "<!DOCTYPE html>"
                + "<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>"
                + "<head><meta charset='utf-8'/></head>"
                + "<body>"
                + option.toString()
                + "</body>"
                + "</html>";
    }

    public static String getEnglishPaperTopicDetailHtml(PaperEnglishTopicDetailEntity entity) {
        return "<!DOCTYPE html>"
                + "<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>"
                + "<head><meta charset='utf-8'/></head>"
                + "<body>"
                + entity.getTitle()
                + "</body>"
                + "</html>";
    }

    public static String getPaperTopicDetailHtml(TopicDetailEntity entity, String maxScore, String topicIndex) {
        List<TopicDetailEntity.OptionsBean> options = entity.getOptions();
        StringBuilder option = new StringBuilder();
        if (options != null) {
            for (TopicDetailEntity.OptionsBean optionsBean : options) {
                option.append(optionsBean.getContent());
            }
        }
        List<TopicDetailEntity.MethodsBean> methods = entity.getMethods();
        StringBuilder method = new StringBuilder();
        if (methods != null) {
            for (TopicDetailEntity.MethodsBean methodsBean : methods) {
                method.append("<span style='color:#B4B4B4;border:1px solid #ccc;border-radius:10px;display:inline-block;word-break:keep-all;margin:4px;padding:4px'>").append(methodsBean.getMethodName()).append("</span>");
            }
        }
        return "<!DOCTYPE html>"
                + "<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>"
                + "<head><meta charset='utf-8'/></head>"
                + "<body>"
                + "<font color='#62B75D' > [第" + topicIndex + "题]:</font>"
                + "<font> 难度:" + entity.getDifficultyDegreeName() + " (本小题满分" + maxScore + "分)</font>"
                + entity.getTitle()
                + method
                + "<hr style='height:5px' color='#DCDCDC'>"
                + "<img width=25 height=25 src='file:///android_asset/ic_paper_analysis.png'/><font>正确答案</font>"
                + "<hr style='height:0.1px' color='#DCDCDC'>"
                + option
                + "<hr style='height:5px' color='#DCDCDC'>"
                + "<img width=25 height=25 src='file:///android_asset/ic_paper_answer.png'/><font>试题解析</font>"
                + "<hr style='height:0.1px' color='#DCDCDC'>"
                + entity.getAnswer()
                + "</body>"
                + "</html>";
    }
}
