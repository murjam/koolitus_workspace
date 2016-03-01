package ee.itcollege.weblist;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ee.itcollege.weblist.entity.Person;
import ee.itcollege.weblist.service.PersonService;

public class PersonsPage extends WebPage {

    @SpringBean
    private PersonService personService;
    
    private static final long serialVersionUID = 1L;
    private WebMarkupContainer listContainer = new WebMarkupContainer("list-container");
    
    Form<Person> form = new Form<Person>("add-person-form",
    		// CompoundPropertyModel asks for field "name" to automatically
    		// update person.getName() person.setName()
    		new CompoundPropertyModel<Person>(new Person()));
    
    
    @SuppressWarnings("serial")
	public PersonsPage(final PageParameters parameters) {
        super(parameters);
        
       
        add(form);
        
        final TextField<String> nameField = new TextField<String>("name");
        
        // this is for adding javascript to component
        nameField.add(new Behavior() {
            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                // wicket by default comes with jQuery
                response.render(new OnDomReadyHeaderItem(String.format("$('#%s').focus()", component.getMarkupId())));
            }
        });
        nameField.setOutputMarkupId(true);
        form.add(nameField);
        
        Button submit = new Button("add-person");
        submit.add(new AjaxFormSubmitBehavior(form, "click") {
            @Override
            public void onSubmit(AjaxRequestTarget target) {
                Person p = (Person) getForm().getModelObject();

                // save person
                personService.save(p);

                // create new person object for form
                form.setModelObject(new Person());
                
                // update list and field
                target.add(listContainer, nameField);
            }
        });
        form.add(submit);
        form.setDefaultButton(submit);


        listContainer.add(new ListView<Person>("person", new LoadableDetachableModel<List<Person>>() {
            @Override
            public List<Person> load() {
                return personService.getPersons();
            }
        }) {
			@Override
            protected void populateItem(ListItem<Person> item) {
                Person person = item.getModelObject();
                item.add(new Label("name", person.getName()));
                item.add(new AjaxLink<Void>("deleteLink") {
					@SuppressWarnings("deprecation")
					@Override
					public void onClick(AjaxRequestTarget target) {
						personService.delete(person);
						target.add(listContainer);
					}
				});
            }
        });
        listContainer.setOutputMarkupId(true);
        add(listContainer);

    }
    
}
