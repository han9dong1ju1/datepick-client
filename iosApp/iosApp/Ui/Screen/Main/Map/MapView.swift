//
//  MapView.swift
//  iosApp
//
//  Created by 박현기 on 2022/01/09.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct MapView: View {
    var body: some View {
        NavigationView {
            VStack {
                
                Text("Map")
                
            }.navigationBarTitle("Map")
        }.navigationViewStyle(DoubleColumnNavigationViewStyle())
    }
}

struct MapView_Previews: PreviewProvider {
    static var previews: some View {
        MapView()
    }
}
