//
// Created by Harry on 2022/01/27.
// Copyright (c) 2022 orgName. All rights reserved.
//

import SwiftUI


struct FeaturedDetailScreenView: View {

    @Environment(\.colorScheme) var colorScheme

    var body: some View {
        VStack {
            Text("FeaturedDetail")
        }.navigationTitle("FeaturedDetail")
                .eraseToAnyView()
    }

    #if DEBUG
    @ObservedObject var iO = injectionObserver
    #endif
}