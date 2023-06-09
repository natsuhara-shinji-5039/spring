package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
	
	// 日付の文字列フォーマット
	private final static DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	// インスタンスフィールド
	private LocalDateTime createdAt; // 投稿日時
	private String title; // タイトル
	private String content; // 内容
	private String feeling; // 気分
	
	// コンストラクタ
	public Post(String title, String content, String feeling) {
		this.title = title;
		this.content = content;
		this.feeling = feeling;
		this.createdAt = LocalDateTime.now(); // 現在日時
	}
	
	// ゲッター
	public String getCreatedAt() {
		return createdAt.format(FMT); // LocalDateTime->String変換
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		if (content.startsWith("https://") || content.startsWith("http://")) {
			return "<a href=" + content + ">" + content + "</a>";
        } else {
		    return content.replaceAll("\n", "<br>"); // 改行文字列-><br>置換
        }
		//return content.replaceAll("\n", "<br>"); // 改行文字列-><br>置換
	}
	
	public String getFeeling() {
		return feeling;
	}
}


