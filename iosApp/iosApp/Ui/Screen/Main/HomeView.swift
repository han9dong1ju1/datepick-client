//
//  HomeView.swift
//  iosApp
//
//  Created by 박현기 on 2022/01/09.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HomeView: View {
    
    init() {
        UITableView.appearance().backgroundColor = UIColor.clear
    }
    
    var body: some View {
        VStack {
            HStack() {
                Image("datepick-logo")
                    .resizable()
                    .scaledToFit()
                    .frame(width : 100, height: 20)
            }.frame(height: 70)
            
            List {
                ForEach((1...100), id: \.self) { i in
                    Text("Item \(i)")
                }
            }.listStyle(.plain)
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
