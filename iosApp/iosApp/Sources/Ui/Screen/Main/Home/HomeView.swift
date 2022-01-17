//
//  HomeView.swift
//  iosApp
//
//  Created by 박현기 on 2022/01/09.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SwiftUIPager

struct HomeView: View {
    
    @StateObject var viewModel = HomeViewModel()
    
    init() {
        UITableView.appearance().backgroundColor = UIColor.clear
        UITableView.appearance().separatorColor = .clear
    }
    
    var body: some View {
        VStack {
            HStack() {
                Image("datepick-logo")
                    .resizable()
                    .scaledToFit()
                    .frame(width : 100, height: 20)
            }.frame(height: 50)
            
            List {
                HomeFeaturedPagerView(featuredList: viewModel.state.featuredList)
                Text("다른 장소")
            }
            .listStyle(.plain)
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
