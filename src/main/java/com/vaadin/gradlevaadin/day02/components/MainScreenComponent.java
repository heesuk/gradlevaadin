package com.vaadin.gradlevaadin.day02.components;

import com.vaadin.gradlevaadin.day02.VaadinUI;
import com.vaadin.gradlevaadin.day02.entity.menu.VaadinNavigator;
import com.vaadin.gradlevaadin.day02.session.UserSession;
import com.vaadin.gradlevaadin.day02.views.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;

import static com.vaadin.gradlevaadin.day02.session.UserSession.signout;

public class MainScreenComponent extends HorizontalLayout {
    public MainScreenComponent(VaadinUI vaadinUI) {
        /*Label label = new Label(UserSession.getUser().getUserNickname());
        final Button signout = new Button("Sign Out");
        signout.addClickListener(e->signOut());
        addComponent(label);
        addComponent(signout);*/

        // View가 동적으로 바뀌어 보이게 되는 빈 Layout
        CssLayout viewContainer = new CssLayout();
        viewContainer.setSizeFull();

        // 네비게이터 생성(로그인한 권한에 맞게 뷰 리스트 등록
        final VaadinNavigator navigator = new VaadinNavigator(vaadinUI.getCurrent(), viewContainer);
        // 메뉴 영역로그인한 권한에 맞게 메뉴 아이템 보여주기
        final VaadinMenuComponent menuArea = new VaadinMenuComponent(navigator);
        // 메뉴영역 + 동적 변경 뷰 영역 순서대로 추가
        addComponents(menuArea, viewContainer);
        // 동적 변경 뷰 영역이 빈 영역 모두 사용하기
        setExpandRatio(viewContainer, 1);

        setSizeFull();

        navigator.navigateTo(UI.getCurrent().getNavigator().getState());

        // URI Fragment별로 view 를 매칭해서 관리하는 Navigator
        /*final Navigator navigator = new Navigator(vaadinUI, viewContainer);
        navigator.addView(DashboardView.VIEW_NAME, new DashboardView());
        navigator.addView(SessionView.VIEW_NAME, new SessionView());
        navigator.addView(AboutView.VIEW_NAME, new AboutView());
        navigator.addView(UserView.VIEW_NAME, new UserView());
        navigator.setErrorView(ErrorView.class);
        // MainScreen의 HorizontalLayout에 view container를 추가
        addComponent(viewContainer);

        // 현재 요청된 주소값에 맞게 viewContainer의 뷰를 동적으로 교제
        navigator.navigateTo(UI.getCurrent().getNavigator().getState());*/
    }

    private void signOut() {
        UserSession.signout();
    }
}
