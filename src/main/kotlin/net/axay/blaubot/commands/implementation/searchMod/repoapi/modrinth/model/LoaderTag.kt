package bot.repoapi.modrinth.model

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LoaderTag(
  /**
   * The SVG icon of a loader
   *
   * **Example**: `"<svg viewBox=\"0 0 276 288\" fill=\"none\" stroke=\"currentColor\"
   * stroke-width=\"23\" stroke-linecap=\"round\" stroke-linejoin=\"round\"><g
   * transform=\"matrix(1,0,0,1,-3302.43,-67.3276)\"><g
   * transform=\"matrix(0.564163,0,0,1.70346,1629.87,0)\"><g
   * transform=\"matrix(1.97801,-0.0501803,0.151517,0.655089,1678.7,-354.14)\"><g><path
   * d=\"M820.011,761.092C798.277,738.875 754.809,694.442 734.36,673.389C729.774,668.668 723.992,663.75
   * 708.535,674.369C688.629,688.043 700.073,696.251 703.288,699.785C711.508,708.824 787.411,788.803
   * 800.523,803.818C802.95,806.597 780.243,781.318 793.957,764.065C799.444,757.163 811.985,752.043
   * 820.011,761.092C826.534,768.447 830.658,779.178 816.559,790.826C791.91,811.191 714.618,873.211
   * 689.659,893.792C677.105,904.144 661.053,896.143 653.827,887.719C646.269,878.908 623.211,853.212
   * 602.539,829.646C596.999,823.332 598.393,810.031 604.753,804.545C639.873,774.253 696.704,730.787
   * 716.673,713.831\"/></g></g></g></g></svg>"`
   */
  public val icon: String,
  /**
   * The name of the loader
   *
   * **Example**: `"fabric"`
   */
  public val name: String,
  /**
   * The project types that this loader is applicable to
   *
   * **Example**: `["mod","modpack"]`
   */
  @SerialName("supported_project_types")
  public val supportedProjectTypes: List<String>,
)
