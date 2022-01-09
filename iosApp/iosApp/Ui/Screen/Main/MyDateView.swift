//
//  MyDateView.swift
//  iosApp
//
//  Created by 박현기 on 2022/01/09.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct MyDateView: View {
    var body: some View {
        NavigationView {
            VStack {
                
                Text("My Date")
                
            }.navigationBarTitle("My Date")
                .animation(.easeOut(duration: 0.3))
        }.navigationViewStyle(DoubleColumnNavigationViewStyle())
    }
}

struct MyDateView_Previews: PreviewProvider {
    static var previews: some View {
        MyDateView()
    }
}
