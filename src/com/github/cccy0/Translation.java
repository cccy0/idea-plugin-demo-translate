package com.github.cccy0;

import java.util.List;

/**
 * @author Zhai
 * 2020/8/20 11:00
 */

public class Translation {
    private Integer status;

    private Content content;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    class Content {
        private String out;

        private List<String> word_mean;

        public String getOut() {
            return out;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public List<String> getWord_mean() {
            return word_mean;
        }

        public void setWord_mean(List<String> word_mean) {
            this.word_mean = word_mean;
        }
    }
}
