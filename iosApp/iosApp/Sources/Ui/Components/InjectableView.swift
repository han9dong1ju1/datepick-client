//
// Created by Harry on 2022/01/25.
// Copyright (c) 2022 orgName. All rights reserved.
//

import SwiftUI


struct InjectableView<Content> : View where Content: View {

    let content : Content

    init(@ViewBuilder content: () -> Content) {
        self.content = content()
    }

    var body: some View {
        content.eraseToAnyView()
    }

    #if DEBUG
    @ObservedObject var iO = injectionObserver
    #endif
}