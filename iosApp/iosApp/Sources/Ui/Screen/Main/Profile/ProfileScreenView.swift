//
//  ProfileView.swift
//  iosApp
//
//  Created by 박현기 on 2022/01/09.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ProfileScreenView: View {
    var body: some View {
        NavigationView {
            
            List {
                
                NavigationLink {
                    SettingsScreenView()
                } label: {
                    SimpleListItem(title: "앱 설정")
                }
                
                NavigationLink {
                    NotificationSettingsScreen()
                } label: {
                    SimpleListItem(title: "알림 설정", subtitle: "앱에서 수신되는 알림을 설정합니다.")
                }
                
            }
            .listStyle(.plain)
            .navigationBarTitle("내 프로필")
            
        }.navigationViewStyle(DoubleColumnNavigationViewStyle())
    }
}

struct ProfileView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileScreenView()
    }
}
