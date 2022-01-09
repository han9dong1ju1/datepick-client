//
//  ProfileView.swift
//  iosApp
//
//  Created by 박현기 on 2022/01/09.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ProfileView: View {
    var body: some View {
        NavigationView {
            VStack {
                
                Text("Profile")
                
            }.navigationBarTitle("Profile")
                .animation(.easeOut(duration: 0.3))
        }.navigationViewStyle(DoubleColumnNavigationViewStyle())
    }
}

struct ProfileView_Previews: PreviewProvider {
    static var previews: some View {
        ProfileView()
    }
}
