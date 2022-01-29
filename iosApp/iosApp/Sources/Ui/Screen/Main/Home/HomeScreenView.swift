//
//  HomeView.swift
//  iosApp
//
//  Created by 박현기 on 2022/01/09.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreenView: View {

    @StateObject var viewModel = HomeScreenViewModel()

    @Environment(\.colorScheme) var colorScheme

    init() {
        UITableView.appearance().backgroundColor = UIColor.clear
        UITableView.appearance().separatorColor = .clear
    }

    var body: some View {
        NavigationView {
            VStack {
                HStack {
                    Image("datepick-logo")
                            .resizable()
                            .renderingMode(.template)
                            .foregroundColor(colorScheme == .dark ? .white : .black)
                            .scaledToFit()
                            .frame(width: 100, height: 20)
                }.frame(height: 50)

                ScrollView {
                    LazyVStack {

                        Spacer().frame(height: 20)

                        MainHomeScreenFeaturedListView(featuredList: viewModel.state.featuredList)

                        Spacer().frame(height: 20)



                    }
                }

            }.navigationBarHidden(true)
        }.eraseToAnyView()
    }

    #if DEBUG
    @ObservedObject var iO = injectionObserver
    #endif
}
