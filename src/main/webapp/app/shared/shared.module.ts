import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { D2GcrmSharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [D2GcrmSharedCommonModule],
  declarations: [HasAnyAuthorityDirective],
  exports: [D2GcrmSharedCommonModule, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class D2GcrmSharedModule {
  static forRoot() {
    return {
      ngModule: D2GcrmSharedModule
    };
  }
}
