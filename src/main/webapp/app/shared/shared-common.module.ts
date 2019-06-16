import { NgModule } from '@angular/core';

import { D2GcrmSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [D2GcrmSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [D2GcrmSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class D2GcrmSharedCommonModule {}
