package com.msg.adm.gui.beans.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.msg.adm.business.data.User;
import com.msg.adm.gui.beans.CreateUserView;
import com.msg.adm.gui.beans.EditUserView;

@FacesConverter(value = "userEditConverter")
public class UserEditConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String userId) {
		ValueExpression vex = context.getApplication().getExpressionFactory()
				.createValueExpression(context.getELContext(), "#{editUserView}", EditUserView.class);

		EditUserView editUserView = (EditUserView) vex.getValue(context.getELContext());
		return editUserView.getSelectedUser(Long.valueOf(userId));

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object user) {
		return ((User) user).getId().toString();
	}

}
