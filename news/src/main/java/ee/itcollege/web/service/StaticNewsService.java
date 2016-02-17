package ee.itcollege.web.service;

import org.springframework.stereotype.Service;

import ee.itcollege.web.entity.News;

@Service
public class StaticNewsService implements NewsService {

	@Override
	public News getNews() {
		News news = new News();
		
		news.title = "Tere!";
		news.url = "www.tere.com";
		
		return news;
	}

}
