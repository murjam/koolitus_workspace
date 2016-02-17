package ee.itcollege.web.page;

import java.io.IOException;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.time.Duration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ee.itcollege.web.entity.News;
import ee.itcollege.web.service.NewsService;

public class FirstPage extends LayoutPage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private NewsService newsService;
	
	Model<Integer> numberModel = new Model<Integer>(0);
	Label label = new Label("version", numberModel);
	
	Model<String> titleModel = new Model<String>();
	Label newsTitle = new Label("newsTitle", titleModel);
	
	Model<String> urlModel = new Model<String>();
	ExternalLink newsLink = new ExternalLink("newsLink", urlModel);
	
	public FirstPage() {
		
		newsLink.add(newsTitle);
		newsLink.setOutputMarkupId(true);
		add(newsLink);
		
		add(new AbstractAjaxTimerBehavior(Duration.seconds(10)) {
			@Override
			protected void onTimer(AjaxRequestTarget target) {
				News news = newsService.getNews();
				titleModel.setObject(news.title);
				urlModel.setObject(news.url);
					
				target.add(newsLink);
				
//				try {
//					
//					Document document = Jsoup.connect("http://postimees.ee").get();
//					
//					Elements links = document.select("h1.frontHeading");
//					Element randomNews = links.get((int)(links.size() * Math.random()));
//					
//					titleModel.setObject(randomNews.text());
//					urlModel.setObject(randomNews.parent().absUrl("href"));
//						
//					target.add(newsLink);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
			
		});
		
		add(label);
		label.setOutputMarkupId(true);

		add(new AjaxLink<Void>("secondLink") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				numberModel.setObject(numberModel.getObject() + 1);
				target.add(label);
			}
		});
		
    }

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
}
