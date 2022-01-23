//
//  HomeView.swift
//  iosApp
//
//  Created by 박현기 on 2022/01/09.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

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
            
            ScrollView {
                
                LazyVStack {
                    
                    Spacer().frame(height:20)
                    
                    HomeFeaturedListView(featuredList: viewModel.state.featuredList)
                        
                    Spacer().frame(height:20)
                    
                    
                    
                }
                
            }
        }
    }
}
