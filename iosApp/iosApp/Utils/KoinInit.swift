//
//  Koin.swift
//  iosApp
//
//  Created by Harry on 2022/01/17.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import presentation

private var _koin: Koin_coreKoin? = nil
var koin: Koin_coreKoin {
    return _koin!
}

func startKoin() {
    _koin = KoinKt.doInitKoin().koin
}
