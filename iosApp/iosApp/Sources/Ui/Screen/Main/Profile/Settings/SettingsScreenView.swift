//
//  SettingsView.swift
//  iosApp
//
//  Created by Harry on 2022/01/15.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SettingsScreenView: View {
    var body: some View {
        List {
            Text("테마 설정")
        }
        .listStyle(.plain)
        .navigationTitle("설정")
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsScreenView()
    }
}
