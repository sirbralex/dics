package com.example.dics.component

interface DiComponent
interface PermanentDiComponent : DiComponent
interface WithKeyOnlyDiComponent : DiComponent

// No instances, just to fit exiting api
sealed interface WeakRefDiComponent : DiComponent

interface ApplicationDiComponent : DiComponent
interface ApplicationPermanentDiComponent : ApplicationDiComponent, PermanentDiComponent

interface ActivityNonConfigDiComponent : DiComponent
interface ActivityNonConfigPermanentDiComponent : ActivityNonConfigDiComponent, PermanentDiComponent
interface ActivityDiComponent : DiComponent
interface ActivityPermanentDiComponent : ActivityDiComponent, PermanentDiComponent

interface FragmentNonConfigDiComponent : DiComponent
interface FragmentNonConfigPermanentDiComponent : FragmentNonConfigDiComponent, PermanentDiComponent
interface FragmentDiComponent : DiComponent
interface FragmentPermanentDiComponent : FragmentDiComponent, PermanentDiComponent
interface FragmentViewDiComponent : DiComponent
interface FragmentViewPermanentDiComponent : FragmentViewDiComponent, PermanentDiComponent


